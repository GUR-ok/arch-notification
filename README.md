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

kafka-console-consumer.bat --bootstrap-server localhost:9091 --topic notification --from-beginning
{"event":"SOME", "id":"2fa85f64-5717-4562-b3fc-2c963f66afa6", "value":2.0}

---
### Очистка пространства:

- `helm uninstall arch_notification`
- `kubectl delete namespace arch-gur`
- `helm uninstall gorelov-kafka`
Пример сообщения в Кафку:

