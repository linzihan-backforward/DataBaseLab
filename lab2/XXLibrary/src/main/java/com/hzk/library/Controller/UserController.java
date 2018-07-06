/**
 * 
 */
package com.hzk.library.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzk.library.Entity.User;
import com.hzk.library.ServiceImpl.BookBorrowServiceImpl;
import com.hzk.library.ServiceImpl.UserServiceImpl;
import com.hzk.library.VO.Reader;

/**
 * @author linzihan
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private  UserServiceImpl userservice;

	@Autowired
	private BookBorrowServiceImpl bookborrowService;
	
	//得到一个用户的基本信息
	@GetMapping("/reader/{id}")
	@ResponseBody
	public Reader getreader(@PathVariable("id") String userid){
	    Reader reader = new Reader();
	    
	    Optional<User> u=userservice.getUserById(userid);
	    
	    if(u.isPresent()){
	    	User user=u.get();
	    	reader.setId(user.getId());
		    reader.setName(user.getName());
		    reader.setGender(user.getGender());
		    reader.setEmail(user.getEmail());
		    reader.setPhoneNumber(user.getPhoneNumber());
		    reader.setReaderID(user.getReaderID());
		    reader.setRole(user.getRole().getRoleName());
		    reader.setState(user.getState().name());
		    reader.setCheckoutAmount(user.getCheckoutAmount());
		    
		    //取得该读者的借书数量
		    reader.setBorrowedBookCnt(bookborrowService.GetBorrowedBookCntForReader(user));	
	    }else{
	    	//该读者不存在
	    	reader.setId("0000");
		    reader.setName("不存在该读者");
		    reader.setGender("");
		    reader.setEmail("");
		    reader.setReaderID("");
		    reader.setPhoneNumber("");
		    reader.setRole("");
		    reader.setState("");
		    reader.setCheckoutAmount(0);
		    reader.setBorrowedBookCnt(0);
	    	
	    }  
	    return reader;
	}
	
	//删除某读者
	@DeleteMapping("/reader/{id}")
   public String hello(String id){
	   userservice.deleteUserByid(id);	
	   return "redirect:/readers";
   }
	
	
	//得到所有读者
	@GetMapping("/readers")
	public String getusers(Model model){
		 List<User> users = userservice.getAllUsers();
		 
		 List<Reader> readers=new ArrayList<>();
		 
		 if(users.size()>0) {   //查到了一个以上的用户，则逐个创建读者。

			 for (User user : users) {
			    Reader reader = new Reader();
			    
			    reader.setId(user.getId());
			    reader.setName(user.getName());
			    reader.setGender(user.getGender());
			    reader.setEmail(user.getEmail());
			    reader.setPhoneNumber(user.getPhoneNumber());
			    reader.setReaderID(user.getReaderID());
			    reader.setRole(user.getRole().getRoleName());
			    reader.setState(user.getState().name());
			    reader.setCheckoutAmount(user.getCheckoutAmount());
			    
			    //取得该读者的借书数量
			    reader.setBorrowedBookCnt(bookborrowService.GetBorrowedBookCntForReader(user));	
			  readers.add(reader);  
		     }
		 }
		model.addAttribute("readers",readers);
	    			
		return "reader/list";
	}
}
