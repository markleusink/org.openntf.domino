/**
 * 
 */
package org.openntf.domino.xsp.helpers;

import java.util.logging.Logger;

import org.openntf.domino.Document;
import org.openntf.domino.utils.DominoUtils;
import org.openntf.domino.utils.XSPUtil;
import org.openntf.domino.xsp.ODAPlatform;

import com.ibm.xsp.model.domino.wrapped.DominoDocument;

/**
 * @author Nathan T. Freeman
 * 
 *         Class of XPages utilities
 */
public class XspUtils {
	@SuppressWarnings("unused")
	private static final Logger log_ = Logger.getLogger(XspUtils.class.getName());

	/**
	 * Constructor
	 */
	private XspUtils() {

	}

	/**
	 * Gets the back-end Document using a DominoDocument datasource, applying changes in front end, and converts to org.openntf.domino
	 * version.<br/>
	 * Avoids the need to catch a NotesException
	 * 
	 * <b>NOTE:<b> In recent experience, this results in save conflicts, no idea why. I would recommend not using (PSW)
	 * 
	 * @param doc
	 *            DominoDocument datasource
	 * @return Document back-end document with front-end values applied, using doc.getDocument(true)
	 * @since org.openntf.domino.xsp 5.0.0
	 * @deprecated (seems to cause save conflicts for some reason, possibly related to it being in a plugin, but god knows when we'll get to
	 *             see enough to try to understand it!)
	 */
	@Deprecated
	public static Document getBEDoc(final DominoDocument doc) {
		Document beDoc;
		try {
			if (ODAPlatform.isAppGodMode(null)) {
				beDoc = (Document) doc.getDocument(true);
			} else {
				beDoc = XSPUtil.wrap(doc.getDocument(true));
			}
		} catch (Throwable e) {
			DominoUtils.handleException(e);
			return null;
		}
		return beDoc;
	}

}
