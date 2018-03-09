import java.util.Properties

import edu.knoldus.Student
import org.apache.kafka.clients.consumer.{ConsumerRecord, KafkaConsumer}
import org.apache.log4j.Logger

import scala.collection.JavaConverters._

object Consumer extends App {
  val log = Logger.getLogger(this.getClass)
  val property = new Properties()
  val topic ="topic1"
  property.put("bootstrap.servers", "localhost:9092")
  property.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  property.put("value.deserializer", "StudentDeserializer")
  property.put("auto.offset.reset","earliest")
  property.put("enable.auto.commit","false")

  property.put("group.id","something")

  val consumer = new KafkaConsumer[String,Student](property)
  consumer.subscribe(java.util.Collections.singletonList(topic))
  while(true) {
    val records = consumer.poll(50)
    for(record: ConsumerRecord[String, Student] <- records.asScala)
      log.info(record.value())
  }


}
