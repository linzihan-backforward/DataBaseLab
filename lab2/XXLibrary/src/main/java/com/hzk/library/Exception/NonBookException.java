/**
 * 
 */
package com.hzk.library.Exception;

/**
 * @author linzihan
 *
 */
public class NonBookException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3494238211129004841L;

	/**
	 * 
	 */
	public NonBookException() {
		super("没有选中任何一本书！");
	}
	
}
