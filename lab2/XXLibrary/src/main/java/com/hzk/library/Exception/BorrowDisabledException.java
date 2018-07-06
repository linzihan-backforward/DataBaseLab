/**
 * 
 */
package com.hzk.library.Exception;

/**
 * @author linzihan
 *
 */
public class BorrowDisabledException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 580443172612420891L;

	/**
	 * 
	 */
	public BorrowDisabledException() {
		super("该读者被人为暂时禁止借书。");
		
	}

}
