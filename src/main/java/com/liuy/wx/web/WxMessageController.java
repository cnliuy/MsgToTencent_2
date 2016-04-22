package com.liuy.wx.web;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.liuy.wx.sdk.msg.InMsgParaser;
import com.liuy.wx.sdk.msg.OutMsgXmlBuilder;
import com.liuy.wx.sdk.msg.in.InImageMsg;
import com.liuy.wx.sdk.msg.in.InLinkMsg;
import com.liuy.wx.sdk.msg.in.InLocationMsg;
import com.liuy.wx.sdk.msg.in.InMsg;
import com.liuy.wx.sdk.msg.in.InTextMsg;
import com.liuy.wx.sdk.msg.in.InVideoMsg;
import com.liuy.wx.sdk.msg.in.InVoiceMsg;
import com.liuy.wx.sdk.msg.in.event.InFollowEvent;
import com.liuy.wx.sdk.msg.in.event.InLocationEvent;
import com.liuy.wx.sdk.msg.in.event.InMenuEvent;
import com.liuy.wx.sdk.msg.in.event.InQrCodeEvent;
import com.liuy.wx.sdk.msg.in.speech_recognition.InSpeechRecognitionResults;
import com.liuy.wx.sdk.msg.out.OutImageMsg;
import com.liuy.wx.sdk.msg.out.OutMsg;
import com.liuy.wx.sdk.msg.out.OutMusicMsg;
import com.liuy.wx.sdk.msg.out.OutNewsMsg;
import com.liuy.wx.sdk.msg.out.OutTextMsg;
import com.liuy.wx.sdk.msg.out.OutVoiceMsg;
import com.liuy.wx.tools.ApiConfig;
import com.liuy.wx.tools.ApiConfigKit;
import com.liuy.wx.tools.ApiConfigKitbb;
import com.liuy.wx.tools.ApiConfigService;
import com.liuy.wx.tools.HttpKit;
import com.liuy.wx.tools.MsgEncryptKit; 




@Controller
public class WxMessageController {
	private static final String helpStr = "\t发送 help 可获得帮助，发送 \"美女\" 可看美女，发送 news 可看新闻，发送 music 可听音乐，你还可以试试发送图片、语音、位置、收藏等信息，看会有什么 。公众号持续更新中，想要更多惊喜欢迎每天关注 ^_^";
	
	
	//@Value("${wx.appId}")
	//private String appId ;
	//@Value("${wx.appSecret}")
	//private String appSecret ;	
	//@Value("${wx.token}")
	//private String token ;
	//@Value("${wx.encodingAesKey}")
	//private String encodingAesKey;	
	//@Value("${wx.messageEncrypt}")
	//private boolean messageEncrypt ;
	

	private String inMsgXml = null;		// 本次请求 xml数据
	
	//protected WxCpConfigStorage wxCpConfigStorage;	
	//protected WxCpService wxCpService;	
	//protected WxCpMessageRouter wxCpMessageRouter;
	
	/**
	 * 如果要支持多公众账号，只需要在此返回各个公众号对应的  ApiConfig 对象即可
	 * 可以通过在请求 url 中挂参数来动态从数据库中获取 ApiConfig 属性值
	 */
//	public ApiConfig getApiConfig() {
//		// 配置微信 API 相关常量
//		System.out.println("her--------------:");
//		ApiConfig ac = new ApiConfig();
//		
//		System.out.println("her2--------ac------:"+ac.getAppId());
//		System.out.println("getApiConfig()+ corpAppId:"+this.corpAppId);
//		ac.setToken(this.corpToken);
//		ac.setAppId(this.corpAppId);  // 设置微信企业号的appid
//		ac.setAppSecret(this.corpAppSecret);// 设置微信企业号的app corpSecret
//		ac.setEncodingAesKey(this.corpEncodingAESKey);
//		/**
//		 *  是否对消息进行加密，对应于微信平台的消息加解密方式：
//		 *  1：true进行加密且必须配置 encodingAesKey
//		 *  2：false采用明文模式，同时也支持混合模式
//		 */
//		ac.setMessageEncrypt(PropKit.getBoolean(encryptMessage, false));
//		
//		return ac;
//	}
	
	@Autowired
	private MsgEncryptKit msgEncryptKit;

	public String getInMsgXml(HttpServletRequest req , String timestamp,String nonce,String msg_signature) {
		
		if (inMsgXml == null) {
			inMsgXml = HttpKit.readIncommingRequestData(req);
			
			// 是否需要解密消息
			if (ApiConfigKitbb.getApiConfig().isMessageencrypt()) {
				inMsgXml = msgEncryptKit.decrypt(inMsgXml,timestamp,nonce,msg_signature);
			}
		}
		return inMsgXml;
	}
	
	

	
	
	/**
	 * 在接收到微信服务器的 InMsg 消息后后响应 OutMsg 消息
	 */
	public String render(OutMsg outMsg ,String timestamp ,String nonce) {
		String outMsgXml = OutMsgXmlBuilder.build(outMsg);
		// 开发模式向控制台输出即将发送的 OutMsg 消息的 xml 内容
		//if (ApiConfigKit.isDevMode()) {
			System.out.println("发送消息:");
			System.out.println(outMsgXml);
			System.out.println("--------------------------------------------------------------------------------\n");
		//}
		boolean messageEncrypt  = apiConfigService.gogetApiConfig().isMessageencrypt();
		// 是否需要加密消息
		if (messageEncrypt) {
			outMsgXml = msgEncryptKit.encrypt(outMsgXml,timestamp, nonce);
		}		
		//renderText(outMsgXml, "text/xml");
		return outMsgXml ;
	}
	

	@Autowired
	private ApiConfigService apiConfigService;
	//@Autowired
	//private ApiConfigKit apiConfigKit;
	
	@RequestMapping("/WxMessage")
	public @ResponseBody String welcome(Map<String, Object> model , HttpServletRequest request) {
				
		ApiConfig apiConfig = apiConfigService.gogetApiConfig() ;
		System.out.println("----0000==a111=="+apiConfig.getAppid() );		 
		//String s1 = apiConfigService.getAppid();
		//String s2 = apiConfigService.getAppsecret();
		//System.out.println("----0000==s1=="+s1 );
		//System.out.println("----0000==s2=="+s2);
		//ApiConfig a = apiConfigKit.gogetApiConfig(s1, s2);
		//System.out.println("----0000==a1=="+a.getAppid() );
		//System.out.println("----0000==a2=="+a.getAppsecret());		
		//ApiConfig s1 = apiConfigService.gogetApiConfig();		
		//String s = ApiConfigKitbb.getApiConfig().getAppid();	
		//ApiConfig result = new ApiConfig("");			
		//ApiConfig ac = ApiConfigKit.getApiConfig();
	
		System.out.println("接收消息:");		
		String timestamp = request.getParameter("timestamp");
		System.out.println("timestamp:"+timestamp);
		String nonce = request.getParameter("nonce");
		System.out.println("nonce:"+nonce);
		String msg_signature = request.getParameter("msg_signature");		
		String inmsgXml00= getInMsgXml(request,timestamp,nonce,msg_signature) ;		
		System.out.println("inmsgXml00:"+inmsgXml00);		
		System.out.println("可在此处存入数据库");
		inmsgXml00="<xml><ToUserName><![CDATA[gh_7e89261c75cf]]></ToUserName>"
				+ "<FromUserName><![CDATA[oNxqNwR4ydOPSZNAFtIHJ28-zmL4]]></FromUserName>"
				+ "<CreateTime>1460792070</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[unsubscribe]]></Event>"
				+ "<EventKey><![CDATA[]]></EventKey>"
				+ "</xml>";
		// 解析消息并根据消息类型分发到相应的处理方法
		
		InMsg msg = InMsgParaser.parse(inmsgXml00); 
		System.out.println("222====="+msg.getMsgType());
		String resultStr = "" ;
		if (msg instanceof InTextMsg){
			System.out.println("in---------");
			resultStr = processInTextMsg((InTextMsg)msg , timestamp , nonce);	
		}
		
		if (msg instanceof InTextMsg)
			resultStr = processInTextMsg((InTextMsg)msg , timestamp , nonce);	
		else if (msg instanceof InImageMsg)
			resultStr = processInImageMsg((InImageMsg)msg , timestamp , nonce);
		else if (msg instanceof InVoiceMsg)
			resultStr = processInVoiceMsg((InVoiceMsg)msg, timestamp , nonce);
		else if (msg instanceof InVideoMsg)
			resultStr = processInVideoMsg((InVideoMsg)msg, timestamp , nonce);
		else if (msg instanceof InLocationMsg)
			resultStr = processInLocationMsg((InLocationMsg)msg, timestamp , nonce);
		else if (msg instanceof InLinkMsg)
			resultStr = processInLinkMsg((InLinkMsg)msg, timestamp , nonce);
		else if (msg instanceof InFollowEvent)
			resultStr = processInFollowEvent((InFollowEvent)msg, timestamp , nonce);
		else if (msg instanceof InQrCodeEvent)
			resultStr = processInQrCodeEvent((InQrCodeEvent)msg, timestamp , nonce);
		else if (msg instanceof InLocationEvent)
			resultStr = processInLocationEvent((InLocationEvent)msg, timestamp , nonce);
		else if (msg instanceof InMenuEvent)
			resultStr = processInMenuEvent((InMenuEvent)msg, msg, timestamp , nonce);
		else if (msg instanceof InSpeechRecognitionResults)
			resultStr = processInSpeechRecognitionResults((InSpeechRecognitionResults)msg, msg, timestamp , nonce);
		else{
			resultStr = "未能识别的消息类型。 消息 xml 内容为：\n" +inmsgXml00;
			System.out.println("未能识别的消息类型。 消息 xml 内容为：\n" +inmsgXml00);	
		}
		
		
		System.out.println("resultStr:"+resultStr);
		//resultStr="999";
		return resultStr;
	}
	
	
	/**
	 * ------------------------------------------------------------
	 * */
	/**
	 * 实现父类抽方法，处理文本消息
	 * 本例子中根据消息中的不同文本内容分别做出了不同的响应，同时也是为了测试  weixin sdk的基本功能：
	 *     本方法仅测试了 OutTextMsg、OutNewsMsg、OutMusicMsg 三种类型的OutMsg，
	 *     其它类型的消息会在随后的方法中进行测试
	 */
	protected String processInTextMsg(InTextMsg inTextMsg ,String timestamp ,String nonce) {
		System.out.println("in method : processInTextMsg()");
		String linkadd ="http://211.148.171.93/InftoTence/";
		String msgContent = inTextMsg.getContent().trim();
		String resultstr = "";
		// 帮助提示
		if ("help".equalsIgnoreCase(msgContent)) {
			OutTextMsg outMsg = new OutTextMsg(inTextMsg);
			outMsg.setContent(helpStr);
			resultstr = render(outMsg,timestamp ,nonce );
		}
		// 图文消息测试
		else if ("news".equalsIgnoreCase(msgContent)) {
			OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
			outMsg.addNews("开发框架1", "现在就加入开发世界 ^_^", linkadd+"/pic/lj.jpg", linkadd+"news/news1.jsp");
			//outMsg.addNews("开发框架2", "全面支持多数源、多方言、多缓", linkadd+"/pic/smc9.jpg","http://www.videojs.com/");
			outMsg.addNews("开发框架2", "全面支持多数源、多方言、多缓", linkadd+"/pic/smc9.jpg",linkadd+"/demo1.jsp"); // linkadd+"news/news2.jsp"
			resultstr = render(outMsg,timestamp ,nonce);
		}
		// 音乐消息测试
		else if ("music".equalsIgnoreCase(msgContent)) {
			OutMusicMsg outMsg = new OutMusicMsg(inTextMsg);
			outMsg.setTitle("贝加尔湖");
			outMsg.setDescription("建议在 WIFI 环境下流畅欣赏此音乐");
			outMsg.setMusicUrl(linkadd+"/bjeh.mp3");
			outMsg.setHqMusicUrl(linkadd+"/bjeh.mp3");
			outMsg.setFuncFlag(true);
			resultstr = render(outMsg,timestamp ,nonce);
		}
		else if ("美女".equalsIgnoreCase(msgContent)) {
			OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
			//outMsg.addNews("我们只看美女", "又一大波美女来袭，我们只看美女 ^_^", "https://mmbiz.qlogo.cn/mmbiz/zz3Q6WSrzq3DmIGiadDEicRIp69r1iccicwKEUOKuLhYgjibyU96ia581gCf5o3kicqz6ZLdsDyUtLib0q0hdgHtZOf4Wg/0", "http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=202080887&idx=1&sn=0649c67de565e2d863bf3b8feee24da0#rd");
			outMsg.addNews("我们只看美女", "又一大波美女来袭，我们只看美女 ^_^", linkadd+"/pic/mv02.png", linkadd+"news/news3.jsp");
			
			//outMsg.addNews("我们只看美女", "又一大波美女来袭，我们只看美女 ^_^", "http://a1.att.hudong.com/26/75/01300000835450126949751126273.jpg", "http://www.baidu.com/");
			// outMsg.addNews("秀色可餐", "JFinal Weixin 极速开发就是这么爽，有木有 ^_^", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq2GJLC60ECD7rE7n1cvKWRNFvOyib4KGdic3N5APUWf4ia3LLPxJrtyIYRx93aPNkDtib3ADvdaBXmZJg/0", "http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200987822&idx=1&sn=7eb2918275fb0fa7b520768854fb7b80#rd");
			
			resultstr = render(outMsg,timestamp ,nonce);
		}
		// 其它文本消息直接返回原值 + 帮助提示
		else {
			OutTextMsg outMsg = new OutTextMsg(inTextMsg);
			outMsg.setContent("\t文本消息已成功接收，内容为： " + inTextMsg.getContent() + "\n\n" + helpStr);
			resultstr = render(outMsg,timestamp ,nonce);
		}
		
		return resultstr ;
	}
	
	/**
	 * 实现父类抽方法，处理图片消息
	 */
	protected String processInImageMsg(InImageMsg inImageMsg, String timestamp ,String nonce) {
		//System.out.println("in method : processInImageMsg()");
		OutImageMsg outMsg = new OutImageMsg(inImageMsg);
		// 将刚发过来的图片再发回去
		outMsg.setMediaId(inImageMsg.getMediaId());
		return render(outMsg, timestamp , nonce);
	}
	
	/**
	 * 实现父类抽方法，处理语音消息
	 */
	protected String processInVoiceMsg(InVoiceMsg inVoiceMsg,String timestamp ,String nonce) {
		//System.out.println("in method : processInVoiceMsg()");
		OutVoiceMsg outMsg = new OutVoiceMsg(inVoiceMsg);
		// 将刚发过来的语音再发回去
		outMsg.setMediaId(inVoiceMsg.getMediaId());
		return render(outMsg, timestamp , nonce);
	}
	
	/**
	 * 实现父类抽方法，处理视频消息
	 */
	protected String processInVideoMsg(InVideoMsg inVideoMsg,String timestamp ,String nonce) {
		/* 腾讯 api 有 bug，无法回复视频消息，暂时回复文本消息代码测试
		OutVideoMsg outMsg = new OutVideoMsg(inVideoMsg);
		outMsg.setTitle("OutVideoMsg 发送");
		outMsg.setDescription("刚刚发来的视频再发回去");
		// 将刚发过来的视频再发回去，经测试证明是腾讯官方的 api 有 bug，待 api bug 却除后再试
		outMsg.setMediaId(inVideoMsg.getMediaId());
		render(outMsg);
		*/
		OutTextMsg outMsg = new OutTextMsg(inVideoMsg);
		outMsg.setContent("\t视频消息已成功接收，该视频的 mediaId 为: " + inVideoMsg.getMediaId());
		return render(outMsg, timestamp , nonce);
	}
	
	/**
	 * 实现父类抽方法，处理地址位置消息
	 */
	protected String processInLocationMsg(InLocationMsg inLocationMsg,String timestamp ,String nonce) {
		OutTextMsg outMsg = new OutTextMsg(inLocationMsg);
		outMsg.setContent("已收到地理位置消息:" +
							"\nlocation_X = " + inLocationMsg.getLocation_X() +
							"\nlocation_Y = " + inLocationMsg.getLocation_Y() + 
							"\nscale = " + inLocationMsg.getScale() +
							"\nlabel = " + inLocationMsg.getLabel());
		return render(outMsg, timestamp , nonce);
	}
	
	/**
	 * 实现父类抽方法，处理链接消息
	 * 特别注意：测试时需要发送我的收藏中的曾经收藏过的图文消息，直接发送链接地址会当做文本消息来发送
	 */
	protected String processInLinkMsg(InLinkMsg inLinkMsg,String timestamp ,String nonce) {
		OutNewsMsg outMsg = new OutNewsMsg(inLinkMsg);
		outMsg.addNews("链接消息已成功接收", "链接使用图文消息的方式发回给你，还可以使用文本方式发回。点击图文消息可跳转到链接地址页面，是不是很好玩 :)" , "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq1ibBkhSA1BibMuMxLuHIvUfiaGsK7CC4kIzeh178IYSHbYQ5eg9tVxgEcbegAu22Qhwgl5IhZFWWXUw/0", inLinkMsg.getUrl());
		return render(outMsg, timestamp , nonce);
	}
	
	/**
	 * 实现父类抽方法，处理关注/取消关注消息
	 */
	protected String processInFollowEvent(InFollowEvent inFollowEvent,String timestamp ,String nonce) {
		OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
		outMsg.setContent("感谢关注 [连接]，为您带来好运 :) \n\n\n " + helpStr);
		// 如果为取消关注事件，将无法接收到传回的信息
		return render(outMsg, timestamp , nonce);
	}
	
	/**
	 * 实现父类抽方法，处理扫描带参数二维码事件
	 */
	protected String processInQrCodeEvent(InQrCodeEvent inQrCodeEvent,String timestamp ,String nonce) {
		OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
		outMsg.setContent("processInQrCodeEvent() 方法测试成功");
		return render(outMsg, timestamp , nonce);
	}
	
	/**
	 * 实现父类抽方法，处理上报地理位置事件
	 */
	protected String processInLocationEvent(InLocationEvent inLocationEvent,String timestamp ,String nonce) {
		OutTextMsg outMsg = new OutTextMsg(inLocationEvent);
		outMsg.setContent("processInLocationEvent() 方法测试成功");
		return render(outMsg, timestamp , nonce);
	}
	
	/**
	 * 实现父类抽方法，处理自定义菜单事件
	 */
	protected String processInMenuEvent(InMenuEvent inMenuEvent,InMsg msg ,String timestamp ,String nonce) {
		if(inMenuEvent.getEventKey().equals("V1001_GOOD")){
			return renderOutTextMsg("谢谢您的鼓励!我们会继续加油!", msg ,timestamp , nonce);
		}else if(inMenuEvent.getEventKey().equals("V1002_VOTE")){
			String openId =inMenuEvent.getFromUserName();
			System.out.println(openId);
			//setAttr("opi", openId);
			return renderOutTextMsg("欢迎抽奖，请点<a href=\"http://211.148.171.93/InftoTence/reward/reward2015-6.jsp?opi="+openId+"\"> [这里]</a>", msg ,timestamp , nonce);
			//render("/votepage/vote2015.jsp");
		}else{
			return renderOutTextMsg("processInMenuEvent() 方法测试成功",msg , timestamp , nonce);
		}
		
		
	}
	public String renderOutTextMsg(String content,InMsg msg ,String timestamp ,String nonce) {
		OutTextMsg outMsg= new OutTextMsg(msg);
		outMsg.setContent(content);
		return render(outMsg,timestamp , nonce);
	}
	

	
	/**
	 * 实现父类抽方法，处理接收语音识别结果
	 */
	protected String processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults,InMsg msg ,String timestamp ,String nonce) {
		return renderOutTextMsg("语音识别结果： " + inSpeechRecognitionResults.getRecognition(), msg,timestamp , nonce);
	}

}
