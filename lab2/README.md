#### 图书管理系统
假设图书馆的工作人员要处理下列日常工作：
	借书：核实读者身份并检查是否存在下述情况：
	该读者借书的数额超标；
	该读者所借的书过期未还；
	该读者曾因借书过期被罚款而未交；
如不存在上述情况，则登记借书信息；
	还书：检查所还图书是否损坏或过期，是则登记罚单信息并打印罚单，在交纳罚金前，不允许该读者继续借书。若图书损坏，注销该图书信息，否则进行还书登记。 
	罚款：根据罚单收取罚金，同时取消该读者的借书限制。 
	图书信息维护：新书上架、旧书下架及图书信息查询。
	读者信息维护：录入、注销、修改及查询读者信息。
此外，图书馆还应向读者提供下列基本功能：
	查询图书信息；
	查询自己的基本信息和借书记录；
	续借；
设计一个B/S或C/S模式的系统实现上述功能。

## 相关环境配置
1、	安装java JDK 1.8
2、	安装STS(Spring tool suite)
3、	配置STS：1、参考“2. Eclipse自动补全功能轻松设置”
（https://blog.csdn.net/u012812482/article/details/51121377）； 2、STS 下JAVA开发环境搭建https://blog.csdn.net/gleamy_ming/article/details/60149597。还有很多，自行学习。
1、	配置项目及项目内各类文件的编码字符集，统一设置为UTF-8。
2、	检查preferences->Maven->Installations下，选用自己安装的Maven，不用STS内置的Maven。
4、	安装和配置Maven：下载最新版本（或者3.7版），解压到某文杰夹，新建环境变量MAVEN_HOME，赋值=你的Maven安装文件夹名。
参考：https://www.cnblogs.com/eagle6688/p/7838224.html
5、	安装和配置mysql 5.7：参考：https://www.cnblogs.com/SamWeb/p/7922490.html，注意其中的配置文件	My.ini，其中有客户端、服务器端各有字符集的配置，一定要设为UTF-8，否则不能处理汉字。
6、	在STS中打开项目内的application.property文件（位于src/main/resources下），修改其中的spring.datasource.username和spring.datasource. password为你所安装的Mysql的登录用户名和密码。
7、	其他所需spring框架和数据库组件，都是利用Maven，在pom.xml文件配置后，自动下载的。
8、	在STS内启动应用的方法：1、导入工程，2打开Boot dashboard窗口（windows->show view->others）输入关键字boot就能找到，选中工程名xxlibrary，点击红色运行按钮即可。 
9、	独立启动应用的方法:找到target/Library_MIS.jar,打开Windows的命令行提示符(cmd.exe),进入target文件夹,输入并运行命令: java -jar Library_mis.jar
10、	客户端启动浏览器，地址栏输入127.0.0.1:8080，显示程序的封面页，并提示登录进入。
11、	演示数据设置了两个人物:张三(密码:1,角色是管理员)和宋江(密码:password,角色是读者).设置了4种书:自己到数据库的booklist表中看,book表中有本图书馆的每本书的编号.
12、	所写的软件系统仍然有很多不足，比如界面简单、功能不丰富、罚款和丢书处理没有做，管理员和读者的权限校验不严格，异常提示不要友好。我有时间再慢慢完善。
