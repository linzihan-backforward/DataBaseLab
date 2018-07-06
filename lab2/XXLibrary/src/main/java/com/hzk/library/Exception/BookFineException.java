/**
 * 
 */
package com.hzk.library.Exception;

/**
 * @author linzihan
 *
 */
public class BookFineException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7052682104054015036L;

	/**
	 * 
	 */
	public BookFineException() {
		super("该读者有罚款未处理，暂时不能继续借书。");
	}

}
