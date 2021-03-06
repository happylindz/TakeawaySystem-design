## 外卖系统设计 Suncafe  
一开始老师叫我们写一个基于JAVA跟咖啡有关的系统，我便想起我们食堂内的咖啡厅，夏天到来时许多同学都会选择到咖啡厅喝喝咖啡，要是能够开发一个类似外卖的系统，同学在宿舍想喝咖啡的就能够派上用场了。基于这个想法，我花了差不多一周的时间进行实现并参加班级答辩。  

运行环境: Eclipse

涉及技术（Java）：

* 使用Swing包和windowbuilder进行UI界面的设计
* 使用Socket包进行简单的局域网通讯
* 使用JDOM2、DOM解析和读入XML文件，并使用XML存储信息
* 使用正则表达式对用户输入进行判断  

客户端实现功能：

* 输入手机号进行验证，通过可进入客户端下订单。
* 商品(咖啡、奶茶)以及所有属性的展示。
* 用户可根据需要将商品放入购物车中。
* 用户可根据实际需要跟卖家进行沟通。
* 用户成功下订单并获得订单信息。 

服务端实现功能：

* 卖家通过帐号密码登陆服务平台。
* 能够实时更新用户订单，并进行订单配送。
* 构建本地服务器，能够跟用户进行通讯。
* 能够察看用户订单的详细内容。

#### 源码: [GourmetCoffee](https://github.com/happylindz/TakeawaySystem-design/)
#### 如果有任何不明白的地方，欢迎向我发issue。  

###截图： 

#### 客户端:
用户通过输入手机号和验证码登陆
![img](https://raw.githubusercontent.com/happylindz/TakeawaySystem-design/master/ShotScreen/1.png) 


用户可根据左边的菜单信息挑选商品
![img](https://raw.githubusercontent.com/happylindz/TakeawaySystem-design/master/ShotScreen/2.png)

用户确认购物车信息并填入个人信息
![img](https://raw.githubusercontent.com/happylindz/TakeawaySystem-design/master/ShotScreen/3.png)

用户有需求可与卖家通讯
![img](https://raw.githubusercontent.com/happylindz/TakeawaySystem-design/master/ShotScreen/4.png)

确认订单 打印订单信息  

![img](https://raw.githubusercontent.com/happylindz/TakeawaySystem-design/master/ShotScreen/5.png)

管理员登陆界面

![img](https://raw.githubusercontent.com/happylindz/TakeawaySystem-design/master/ShotScreen/6.png)
