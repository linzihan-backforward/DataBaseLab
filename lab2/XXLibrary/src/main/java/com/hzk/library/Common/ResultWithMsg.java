/**
 * 
 */
package com.hzk.library.Common;

/**
 * 通用的结果返回值：包含result、message
 *
 */
public class ResultWithMsg {
   private boolean result;
   private String message;
   private int bookid;
/**
 * @param result
 * @param message
 */
public ResultWithMsg(boolean result, String message) {
	super();
	this.result = result;
	this.message = message;
}

public boolean isResult() {
	return result;
}

public String getMessage() {
	return message;
}

public int getBookid() {
	return bookid;
}

public void setBookid(int bookid) {
	this.bookid = bookid;
}

@Override
public String toString() {
	return "ResultWithMsg [result=" + result + ", message=" + message + ", bookid=" + bookid + "]";
}
   
}
