/**
 * 
 */
package com.hzk.library.Exception;

/**
 * @author linzihan
 *
 */
public class BorrowOverTimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4152154057759403008L;

	/**
	 * 
	 */
	public BorrowOverTimeException() {
		super("该读者有超期书未处理，暂时不能继续借书。");
	}

}
