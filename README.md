# Прочитать первым - API  

---
### Регистрация заказа
POST **/**  

Body JSON  
{  
"clientId": Long, required  
"employerId": Long, required  
"productId": Long, required  
"productPrice": Integer, required  
}  

Responses    
* 200, Order (JSON)
* 400, Одно или несколько из полей пустые
---
### Получить заказ по номеру
GET **/{id}** | id - тип Long, номер заказа

Responses
* 200, Order (JSON)
* 404, Заказ под этим номером не существует
---
### Принять заказ в работу по номеру
PATCH **/accept/{id}** | id - тип Long, номер заказа  

Body JSON  
{   
"employerId": Long, required  
}

Responses
* 200, Order (JSON)
* 404, Заказ под этим номером либо закрыт(отменен, выдан), либо не зарегистрирован
* 400, Одно или несколько полей пустые
---
### Установить статус заказа "Готов" по номеру
PATCH **/ready/{id}** | id - тип Long, номер заказа  

Body JSON  
{   
"employerId": Long, required  
}

Responses
* 200, Order (JSON)
* 404, Заказ под этим номером либо закрыт(отменен, выдан), либо не зарегистрирован
* 400, Одно или несколько полей пустые
---
### Выдать заказ по номеру
PATCH **/give/{id}** | id - тип Long, номер заказа 

Body JSON  
{   
"employerId": Long, required  
}

Responses
* 200, Order (JSON)
* 404, Заказ под этим номером либо закрыт(отменен, выдан), либо не зарегистрирован
* 400, Одно или несколько полей пустые
---
### Отменить заказ по номеру
PATCH **/cancel/{id}** | id - тип Long, номер заказа 

Body JSON  
{   
"employerId": Long, required  
"cancelReason": String, required  
}

Responses
* 200, Order (JSON)
* 404, Заказ под этим номером либо закрыт(отменен, выдан), либо не зарегистрирован
* 400, Одно или несколько полей пустые
---

