package com.github.ejb.demo;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.github.ejb.service.EjbBeanService;

public class EjbClient {

	public static void main(String[] args) {
		Properties props=new Properties();//jndi 
		//设置 jndi的环境信息
		//设置JNDI驱动的类名
		props.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");//jndi连接工厂--jboss通信
		//命名服务提供者的URL 
		props.setProperty("java.naming.provider.url", "localhost:1099");//jboss 连接地址
		try{
			InitialContext ctx=new InitialContext(props);
			//当EJB发布到Jboss 时，如果我们没有为它指定全局JNDI名称或修改过其默认EJB名称，Jboss就会按照默认的命名规则为EJB生成全局JNDI名称
			//如果把EJB应用打包成后缀为*.jar的模块文件，默认的全局JNDI名称是
			//本地接口：EJB-CLASS-NAME/local
			//远程接口：EJB-CLASS-NAME/remote

			EjbBeanService bean=(EjbBeanService)ctx.lookup("EjbBeanServiceImpl/remote");//Ejb
			System.out.println(bean.sayHi("shanghai"));
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
}
