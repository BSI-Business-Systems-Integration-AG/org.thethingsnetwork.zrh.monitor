package org.thethingsnetwork.zrh.monitor.client.ui;

import java.util.List;

import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.TEXTS;

/**
 * <h3>{@link TheThingsNetworkOutline}</h3>
 *
 * @author mzi
 */
@Order(1000)
public class TheThingsNetworkOutline extends AbstractOutline {

	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		super.execCreateChildPages(pageList);
				
		GatewayTablePage gatewayPage;	
//		NodeTablePage nodePage;
		MessageTablePage messagePage;
		
		pageList.add(new HeatmapPage());
		
//		pageList.add(new NoisemapPage());
//
//		gatewayPage = new GatewayTablePage(TEXTS.get("MyGateways"));
//		gatewayPage.setFavoritesPage(true);
//		gatewayPage.setExpanded(true);
//		pageList.add(gatewayPage);
//
//		nodePage = new NodeTablePage(TEXTS.get("MyNodes"));
//		nodePage.setFavoritesPage(true);
//		nodePage.setExpanded(true);
//		pageList.add(nodePage);

		gatewayPage = new GatewayTablePage(TEXTS.get("AllGateways"));
		gatewayPage.setFavoritesPage(false);
		pageList.add(gatewayPage);
		
//		nodePage = new NodeTablePage(TEXTS.get("AllNodes"));
//		nodePage.setFavoritesPage(false);
//		pageList.add(nodePage);
		
		messagePage = new MessageTablePage(TEXTS.get("AllMessages"));
		pageList.add(messagePage);		
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("TTN");
	}

	@Override
	protected String getConfiguredIconId() {
		return AbstractIcons.World;
	}
}
