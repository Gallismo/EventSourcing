# Прочитать первым - API  

---
POST /{id} | id - тип Long, номер заказа для регистрации  
Body JSON  
{  
"clientId": Long,  
"employerId": Long,  
"productId": Long,  
"productPrice": Integer  
}  

Responses    
* 200, Order (JSON)
* 422, Заказ под этим номером был ранее зарегистрирован
---
GET /{id} | id - тип Long, номер заказа

Responses
* 200, Order (JSON)
* 404, Заказ под этим номером не существует
---
PATCH /accept/{id} | id - тип Long, номер заказа для принятия в работу 
Body JSON  
{   
"employerId": Long,  
}

Responses
* 200, Order (JSON)
* 404, Заказ под этим номером либо закрыт(отменен, выдан), либо не зарегистрирован
---
PATCH /ready/{id} | id - тип Long, номер заказа для готовности  
Body JSON  
{   
"employerId": Long,  
}

Responses
* 200, Order (JSON)
* 404, Заказ под этим номером либо закрыт(отменен, выдан), либо не зарегистрирован
---
PATCH /give/{id} | id - тип Long, номер заказа для выдачи  
Body JSON  
{   
"employerId": Long,  
}

Responses
* 200, Order (JSON)
* 404, Заказ под этим номером либо закрыт(отменен, выдан), либо не зарегистрирован
---
PATCH /cancel/{id} | id - тип Long, номер заказа для регистрации  
Body JSON  
{   
"employerId": Long,  
"cancelReason": String  
}

Responses
* 200, Order (JSON)
* 404, Заказ под этим номером либо закрыт(отменен, выдан), либо не зарегистрирован
---

