Микросервис Уведомлений

Сборка и установка в minikube
1) `gradle build`
2) `docker build -t gurok/arch_notification_2 .`
3) `docker push gurok/arch_notification_2`
4) `kubectl create namespace arch-gur`
5) `helm install gorelov-kafka ./deployment/kafka/`
6) `helm install arch_notification ./deployment/app/`
   `kubectl get pods -n arch-gur`

---

Для локального поднятия кафки: `docker-compose-kafka up`

Пример сообщения в Кафку:

{"event":"DEPOSIT_ACCEPTED", "accountId":"2fa85f64-5717-4562-b3fc-2c963f66afa6", "value":199.99}
{"event":"ORDER_PAID", "orderId":"2fa85f64-5717-4562-b3fc-2c963f66afa6", "accountId":"3bc85f63-6718-4562-f3fd-1c063f77add9"}
{"event":"PAYMENT_FAIL", "orderId":"2fa85f64-5717-4562-b3fc-2c963f66afa6", "accountId":"3bc85f63-6718-4562-f3fd-1c063f77add9"}
{"event":"BROKERAGE_NOTIFICATION", "accountId":"35c4fcd8-cfcf-4679-b57d-b1fb969bbcfb", "message":"Для подтверждения заявки введите код 6544 Договор доступен по ссылке: http://arch.homework:30002/agreementbucket/233077442972086615_67467.pdf?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minio_access_key%2F20221207%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20221207T150611Z&X-Amz-Expires=7200&X-Amz-SignedHeaders=host&X-Amz-Signature=24acc069ff0111d5aa3403db349aded26d3150e218ccbd721b5ed4ac648292f1"}---

### Очистка пространства:

- `helm uninstall arch_notification`
- `kubectl delete namespace arch-gur`
- `helm uninstall gorelov-kafka`
Пример сообщения в Кафку:

