# 图书借阅管理系统

#### 介绍

Spring Boot + MyBatis + thymeleaf + MySQL + jQuery + ajax等实现轻便的图书借阅管理系统

管理员操作进行用户借阅，借阅出的图书在归还之前不能删除，同一个人（姓名+手机号）在图书归还之前不能再次借阅同一本图书，并且采用逻辑删除来处理已经删除的图书在借阅记录中的回显问题（图书已经被删除，但保留之前这本图书的借阅记录，只是不再提供借阅）。
另外还有图书类型分支，添加图书时下拉框选择图书类型，图书类型也可以进行增删改查（此处仍采用逻辑删除处理）。
滞纳金=实际还书时间-计划还书时间
![主界面](https://images.gitee.com/uploads/images/2020/1230/170149_6be82396_8068525.png "主界面.png")
![登录界面](https://images.gitee.com/uploads/images/2020/1230/170225_5dba04e7_8068525.png "登录界面.png")
![借阅](https://images.gitee.com/uploads/images/2020/1230/170251_2e46bf0e_8068525.png "借阅.png")
![还书](https://images.gitee.com/uploads/images/2020/1230/170329_a26ace7f_8068525.png "还书.png")
#### 软件架构

开发环境：IDEA + JDK1.8 + Navicat + Maven


#### 安装教程

1.  导入项目，刷新Maven依赖。
2.  更改yml文件中数据库连接配置
3.  导入数据库表结构与数据

#### 使用说明

1.  登录用户名admin，密码1

#### 结构说明

--图书管理
    --图书列表
    --借阅记录
    --图书类型

