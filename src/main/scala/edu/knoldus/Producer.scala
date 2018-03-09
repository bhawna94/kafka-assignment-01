package edu.knoldus


  import java.util.Properties

  import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

  object Producer extends App {
    val property = new Properties;
    val topic = "topic1"

    property.put("bootstrap.servers", "localhost:9092")
    property.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    property.put("value.serializer", "edu.knoldus.StudentSerializer")
    val producer = new KafkaProducer[String,Student](property)
    val student = List("Bhawna")
    for(id <-0 to student.size-1) {
      val record = new ProducerRecord[String, Student](topic,id.toString,Student(id,student(id)))
      producer.send(record)
    }
     println("message has been written")
    producer.close
  }
