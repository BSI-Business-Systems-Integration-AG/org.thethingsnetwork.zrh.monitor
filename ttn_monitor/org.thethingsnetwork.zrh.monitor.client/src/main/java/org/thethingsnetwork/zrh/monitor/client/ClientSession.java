package org.thethingsnetwork.zrh.monitor.client;

import java.util.Locale;

import org.eclipse.scout.rt.client.AbstractClientSession;
import org.eclipse.scout.rt.client.IClientSession;
import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.shared.services.common.code.CODES;
import org.thethingsnetwork.zrh.monitor.client.ui.Desktop;

/**
 * <h3>{@link ClientSession}</h3>
 *
 * @author mzi
 */
public class ClientSession extends AbstractClientSession {

	public ClientSession() {
		super(true);
	}

	/**
	 * @return The {@link IClientSession} which is associated with the current thread, or <code>null</code> if not found.
	 */
	public static ClientSession get() {
		return ClientSessionProvider.currentSession(ClientSession.class);
	}

	@Override
	protected void execLoadSession() {
		execInitLocale();
		CODES.getAllCodeTypes("org.eclipsescout.demo.widgets.shared");
		setDesktop(new Desktop());
	}

	/**
	 * Sets the session locale <i>before</i> the desktop is created. The default implementation sets the locale to
	 * {@link Locale#ENGLISH} to get a consistent state across the entire widget application (for most languages, not all
	 * tests are localized, except for English). Subclasses may override this method.
	 */
	protected void execInitLocale() {
		setLocale(Locale.ENGLISH);
	}
}
