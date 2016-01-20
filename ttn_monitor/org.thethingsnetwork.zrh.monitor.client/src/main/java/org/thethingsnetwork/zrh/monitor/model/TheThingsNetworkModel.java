package org.thethingsnetwork.zrh.monitor.model;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// TODO initially populate gateways using http://ttnstatus.org/gateways
public class TheThingsNetworkModel {
	public static int MESSAGE_QUEUE_SIZE = 100;
	
	private Map<String, Gateway> m_gateway = null;
	private Map<String, Node> m_node = null;
	
	public TheThingsNetworkModel() {
		reset();
	}
	
	public void addMessage(String message) {
		Message m = new Message(message);
		
		if(m.parsedOk()) {
			if(m.isNodeMessage()) {
				updateNodes(m, message);
			}
			
			updateGateways(m, message);
		}
	}
	
	private void updateGateways(Message message, String messageText) {
		String eui = message.getGatewayEui();
		Gateway g = m_gateway.get(eui);
		
		// add new gateway if this is a gateway message
		if(g == null && !message.isNodeMessage()) {
			g = new Gateway(messageText, MESSAGE_QUEUE_SIZE);
			m_gateway.put(eui, g);
		}
		
		if(g != null) {
			g.addMessage(message);
		}
	}
	
	private void updateNodes(Message message, String messageText) {
		String eui = message.getNodeEui();
		Node n = m_node.get(eui);
		
		// add new node
		if(n == null) {
			n = new Node(messageText, MESSAGE_QUEUE_SIZE);
			m_node.put(eui, n);
		}
		
		if(n != null) {
			n.addMessage(message);
		}
	}

	/**
	 * @return a copy of the set of the currently known gateway eui. copy is needed to avoid concurrent update issues 
	 */
	public Set<String> getGatewayEuis() {
		return m_gateway.keySet();
	}
	
	public Gateway getGateway(String eui) {
		return m_gateway.get(eui);
	}

	public Set<String> getNodeEuis() {
		return m_node.keySet();
	}
	
	public Node getNode(String eui) {
		return m_node.get(eui);
	}
	
	public void reset() {
		m_gateway = new ConcurrentHashMap<String, Gateway>();
		m_node = new ConcurrentHashMap<String, Node>();
	}
}