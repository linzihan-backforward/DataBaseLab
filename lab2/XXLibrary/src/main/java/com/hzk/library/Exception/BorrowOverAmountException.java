/**
 * 
 */
package com.hzk.library.Exception;

/**
 * @author linzihan
 *
 */
public class BorrowOverAmountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8839870629648311989L;

	/**
	 * @param message
	 */
	public BorrowOverAmountException() {
		super("拟借书数量超出了最大可借书数！");
	}

	
}
