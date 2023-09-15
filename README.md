# Прочитать первым - API  

---
POST /{id} | id - тип Long, номер заказа для регистрации  
Body JSON  
{  
"clientId": Long, required  
"employerId": Long, required  
"productId": Long, required  
"productPrice": Integer, required  
}  

Responses    
* 200, Order (JSON)
* 422, Заказ под этим номером был ранее зарегистрирован
* 400, Одно или несколько из полей пустые
---
GET /{id} | id - тип Long, номер заказа

Responses
* 200, Order (JSON)
* 404, Заказ под этим номером не существует
---
PATCH /accept/{id} | id - тип Long, номер заказа для принятия в работу 
Body JSON  
{   
"employerId": Long, required  
}

Responses
* 200, Order (JSON)
* 404, Заказ под этим номером либо закрыт(отменен, выдан), либо не зарегистрирован
* 400, Одно или несколько полей пустые
---
PATCH /ready/{id} | id - тип Long, номер заказа для готовности  
Body JSON  
{   
"employerId": Long, required  
}

Responses
* 200, Order (JSON)
* 404, Заказ под этим номером либо закрыт(отменен, выдан), либо не зарегистрирован
* 400, Одно или несколько полей пустые
---
PATCH /give/{id} | id - тип Long, номер заказа для выдачи  
Body JSON  
{   
"employerId": Long, required  
}

Responses
* 200, Order (JSON)
* 404, Заказ под этим номером либо закрыт(отменен, выдан), либо не зарегистрирован
* 400, Одно или несколько полей пустые
---
PATCH /cancel/{id} | id - тип Long, номер заказа для регистрации  
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

