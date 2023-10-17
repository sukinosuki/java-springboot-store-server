## 环境

- java17
- spring boot2
- mysql8

### application.xml
`localhost:3306` 替换成自己的mysql连接

`spring_boot3_java_test` 替换成自己的数据库

username和password替换成自己的

```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/spring_boot3_java_test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root # 替换成自己的
    password: 233233 # 替换成自己的
```

## 已实现接口
### 管理端
#### 授权 [Controller.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fdemo%2Fadmin%2Fmodules%2Fauth%2FController.java)
- 登录: POST: /admin/api/v1/auth/login
- 注册: POST: /admin/api/v1/auth/register
- 获取登录用户信息: GET: /admin/api/v1/auth/info

#### 权限节点 [Controller.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fdemo%2Fadmin%2Fmodules%2Fsys_permission%2Fhttp%2FController.java)
- 列表: GET: /admin/api/v1/sys-permission
- 新增: POST: /admin/api/v1/sys-permission
- 更新: PUT: /admin/api/v1/sys-permission/:id
- 删除: DELETE: /admin/api/v1/sys-permission/:id

#### 角色 [Controller.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fdemo%2Fadmin%2Fmodules%2Fsys_role%2Fhttp%2FController.java)
- 列表: GET: /admin/api/v1/sys-role
- 新增: POST: /admin/api/v1/sys-role
- 更新: PUT: /admin/api/v1/sys-role/:id
- 删除: DELETE: /admin/api/v1/sys-role/:id

#### 系统用户 [Controller.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fdemo%2Fadmin%2Fmodules%2Fsys_user%2Fhttp%2FController.java)
- 列表: GET: /admin/api/v1/sys-user
- 新增: POST: /admin/api/v1/sys-user
- 更新: PUT: /admin/api/v1/sys-user/:id
- 删除: DELETE: /admin/api/v1/sys-user/:id

#### 导航菜单 [Controller.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fdemo%2Fadmin%2Fmodules%2Fnav_menu%2Fhttp%2FController.java)
- 列表: GET: /admin/api/v1/nav-menu
- 新增: POST: /admin/api/v1/nav-menu
- 更新: PUT: /admin/api/v1/nav-menu/:id
- 删除: DELETE: /admin/api/v1/nav-menu/:id

#### 商品分类 [Controller.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fdemo%2Fadmin%2Fmodules%2Fgoods_category%2Fhttp%2FController.java)
- 列表: GET: /admin/api/v1/goods-category
- 新增: POST: /admin/api/v1/goods-category
- 更新: PUT: /admin/api/v1/goods-category/:id
- 删除: DELETE: /admin/api/v1/goods-category/:id

#### 商品 [Controller.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fdemo%2Fadmin%2Fmodules%2Fgoods%2Fhttp%2FController.java)
- 列表: GET: /admin/api/v1/goods
- 新增: POST: /admin/api/v1/goods
- 更新: PUT: /admin/api/v1/goods/:id
- 删除: DELETE: /admin/api/v1/goods/:id

## 其它

1. 实体类属性都是public的

## resources文件说明

mysql的模型文件:

[resources/sql/java-springboot-store-server-model.ndm2](src%2Fmain%2Fresources%2Fsql%2Fjava-springboot-store-server-model.ndm2)

![mysql_model.png](snapshot%2Fmysql_model.png)

---

mysql模型导出的sql文件: 

[resources/sql/shop.sql](src%2Fmain%2Fresources%2Fsql%2Fshop.sql)

---

postman导出的测试接口
[resources/postman/java-shop.postman_collection.json](src%2Fmain%2Fresources%2Fpostman%2Fjava-shop.postman_collection.json)

---