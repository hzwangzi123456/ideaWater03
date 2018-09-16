package com.common.util;


/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class MessageUtil {

	public static final String BAR_APPLY_AGENT_MSG="%s（%s）向您发送了托管请求，请您及时处理。";
	
	public static final String BAR_CANCEL_APPLY_AGENT_MSG="%s（%s）取消了向您托管的请求。";
	
	public static final String AGENT_AGREE_JOIN_MSG="加盟商（%s）通过了您发送托管的请求，您已成为其旗下的VIP网吧。";
	
	public static final String AGENT_REFUSE_JOIN_MSG="加盟商（%s）拒绝了您发送托管的请求，您可以选择向召唤官方申请托管。";
	
	public static final String AGENT_CANCEL_JOIN_MSG="加盟商（%s）取消托管您的网吧，您可以选择向召唤官方申请托管。";
	
	public static final String BAR_CANCEL_JOIN_MSG="您旗下的网吧%s（%s）已解除了与您的托管关系。";
	
	public static final String AGENT_SET_BAR_TIME_MSG="加盟商（%s）重新设置了您网吧的过期时间为%s。";
	/**
	 * 未读
	 */
	public static final int STATE_NOT_READ=0;
	
	/**
	 * 已读
	 */
	public static final int STATE_HAS_READ=1;
	
	/**
	 * 网吧消息
	 */
	public static final int TYPE_BAR=1;
	
	/**
	 * 加盟商消息
	 */
	public static final int TYPE_AGENT=2;
	
	/**
	 * 自然人消息
	 */
	public static final int TYPE_PERSON=3;
}
