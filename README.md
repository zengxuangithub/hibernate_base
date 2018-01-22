# hibernate基础实例

- 下载相关jar包  将解压文件夹中的lib/required 中的所有jar包导入项目
- 导入数据库jar包
- 开发一个持久化对象Good 该对象是一个标准的javaBean
- hibernate配置文件     **hibernate.cfg.xml**
```

<!DOCTYPE hibernate-configuration PUBLIC     
          "-//Hibernate/Hibernate Configuration DTD 3.0//EN"     
          "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">     
     
    <hibernate-configuration>     
    <!--表明以下的配置是针对session-factory配置的，SessionFactory是Hibernate中的一个类，这个类主要负责保存HIbernate的配置信息，以及对Session的操作-->     
      <session-factory>        
      <!--配置数据库的驱动程序，Hibernate在连接数据库时，需要用到数据库的驱动程序-->     
          <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver </property>     
      <!--设置数据库的连接url:jdbc:mysql://localhost/hibernate,其中localhost表示mysql服务器名称，此处为本机，    hibernate是数据库名-->      
            <property name="hibernate.connection.url">jdbc:mysql://localhost/hibernate
            </property>  
    <!--连接数据库是用户名-->     
          <property name="hibernate.connection.username">root</property>     
          <!--连接数据库是密码-->     
          <property name="hibernate.connection.password">password</property>            
          <!--数据库连接池的大小-->     
          <property name="hibernate.connection.pool.size">20 </property>            
        <!--是否在后台显示Hibernate用到的SQL语句，开发时设置为true，便于差错，程序运行时可以在Eclipse的控制台显示Hibernate的执行Sql语句。项目部署后可以设置为false，提高运行效率-->     
        <property name="hibernate.show_sql">true </property>     
        <!--jdbc.fetch_size是指Hibernate每次从数据库中取出并放到JDBC的Statement中的记录条数。Fetch Size设的越大，读数据库的次数越少，速度越快，Fetch Size越小，读数据库的次数越多，速度越慢-->     
        <property name="jdbc.fetch_size">50 </property>     
        <!--jdbc.batch_size是指Hibernate批量插入,删除和更新时每次操作的记录数。Batch Size越大，批量操作的向数据库发送Sql的次数越少，速度就越快，同样耗用内存就越大-->     
        <property name="jdbc.batch_size">23 </property>     
        <!--jdbc.use_scrollable_resultset是否允许Hibernate用JDBC的可滚动的结果集。对分页的结果集。对分页时的设置非常有帮助-->     
        <property name="jdbc.use_scrollable_resultset">false </property>     
        <!--connection.useUnicode连接数据库时是否使用Unicode编码-->     
        <property name="Connection.useUnicode">true </property>     
        <!--connection.characterEncoding连接数据库时数据的传输字符集编码方式，最好设置为gbk，用gb2312有的字符不全-->     
    <property name="connection.characterEncoding">utf-8 </property>          
            
        <!--hibernate.dialect 只是Hibernate使用的数据库方言,就是要用Hibernate连接那种类型的数据库服务器。-->     
          <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect </property>     
        <!--指定映射文件为“hibernate/ch1/UserInfo.hbm.xml”-->            
          <mapping resource="main/Good.hbm.xml"/>     
  </session-factory>     
  </hibernate-configuration>        
      


```



- 持久化对象映射配置文件 Good.hbm.xml    其中Good为持久化对象名
```
<?xml version="1.0" encoding='UTF-8'?>  
  
<!DOCTYPE hibernate-mapping PUBLIC   
     "-//Hibernate/Hibernate Mapping DTD 3.0//EN"   
     "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">  
<hibernate-mapping package="main">  
   <class name="Good" table="goods">  
      <id name="id" column="id" >  
          <generator class="identity"/>  
      </id> 
      <property name="title"  column="title"  ></property> 
   	  <property name="price"  column="price"  ></property> 
      <property name="taobao_price"  column="taobao_price"  ></property> 
      <property name="quan_num"  column="quan_num"  ></property> 
      <property name="fan_num"  column="fan_num"  ></property> 
      <property name="url"  column="url"  ></property> 
      <property name="goods_type"  column="goods_type"></property> 
      <property name="pic_url"  column="pic_url"  ></property> 
    
      <property name="kinds"  column="kinds"  ></property> 
    
    </class>  
</hibernate-mapping>  

```


-  在业务逻辑中操作持久化对象
```

 Configuration conf=new Configuration().configure();
       SessionFactory sessionFactory=conf.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		Good good=new Good();
	
		good.setUrl("url_test");
		//省略其他set
		
		session.save(good);
		transaction.commit();
		session.close();
		sessionFactory.close();
```