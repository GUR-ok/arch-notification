Микросервис Уведомлений

Сборка и установка в minikube
1) `gradle build`
2) `docker build -t gurok/arch_notification .`
3) `docker push gurok/arch_notification`
4) `kubectl create namespace arch-gur`
5) `helm install gorelov-kafka ./deployment/kafka/`
6) `helm install arch_notification ./deployment/app/`
   `kubectl get pods -n arch-gur`

---

Для локального поднятия кафки: `docker-compose-kafka up`

Пример сообщения в Кафку:

{"event":"ORDER_PAID", "orderId":"2fa85f64-5717-4562-b3fc-2c963f66afa6"}

---
### Очистка пространства:

- `helm uninstall arch_notification`
- `kubectl delete namespace arch-gur`
- `helm uninstall gorelov-kafka`
Пример сообщения в Кафку:

