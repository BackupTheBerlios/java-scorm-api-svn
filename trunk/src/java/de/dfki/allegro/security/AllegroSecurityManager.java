/**
 * 
 */
package de.dfki.allegro.security;

import java.rmi.RMISecurityManager;
import java.security.Permission;
import java.util.logging.Level;
import java.util.logging.Logger;

/** <code>SecurityManager</code> that gives additional permissions
 *  to our application. This is needed for example for the RMI
 *  access.
 * 
 * @author Timo Scheuer
 *
 */
public class AllegroSecurityManager extends RMISecurityManager {

	/** The port which is used for RMI access.*/
	public static final int RMI_PORT = 1099;
	
	
	
	/** Ctor.
	 * 
	 */
	public AllegroSecurityManager() {
		super();
	}
	
	/** Check connection access.
	 * 
	 * @param host  the host name
	 * @param port  the port
	 */
	public void checkAccept(String host, int port) {
		System.out.println("Checking security: checkAccept for " + host + ":" + port + "...");
		//if (port == RMI_PORT) {
			System.out.println("access granted");
			return;
			/*
		}
		System.out.println("access denied");
		super.checkAccept(host, port);
		*/
	}

	public void checkAwtEventQueueAccess() {
		Logger.getLogger("de.dfki.allegro").logp(Level.INFO, "AllegroSecurityManager",
				"checkAwtEventQueueAccess", "method call");
	}
	
	public void checkPermission(Permission perm) {
		Logger.getLogger("de.dfki.allegro").logp(Level.INFO, "AllegroSecurityManager",
				"checkPermission", "method call; param=" + perm);
	}
	
	/** Check connection access.
	 * 
	 * @param host  the host name
	 * @param port  the port
	 */
	public void checkConnect(String host, int port) {
		System.out.println("Checking security: checkConnect for " + host + ":" + port + "...");
		//if (port == RMI_PORT) {
			System.out.println("access granted");
			return;
			/*
		}
		System.out.println("access denied");
		super.checkConnect(host, port);
		*/
	}

}
