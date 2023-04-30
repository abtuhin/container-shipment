package com.kn.containershipment.config

import com.kn.containershipment.model.Action
import com.kn.containershipment.model.PlanTemplate
import com.kn.containershipment.model.TemperatureRange
import com.kn.containershipment.repository.ActionRepository
import com.kn.containershipment.repository.TemperatureRangeRepository
import com.kn.containershipment.repository.TemplateRepository
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class Config {

    val topicExchangeName: String = "spring-boot-exchange";

    val queueName: String = "spring-boot";

    @Bean
    @Throws(Exception::class)
    fun run(templateRepository: TemplateRepository,
            actionRepository: ActionRepository,
            temperatureRangeRepository: TemperatureRangeRepository
    ): CommandLineRunner {
        return CommandLineRunner {
            val temperatureRange = TemperatureRange(id = 1, min = -20, max = -10)

            temperatureRangeRepository.save(temperatureRange)

            val action1 = Action(id = 1, name = "shipment is taken from customer")
            val action2 = Action(id = 2, name = "shipment is on the way")
            val action3 = Action(id = 3, name = "shipment is arrived to destination")
            val action4 = Action(id = 4, name = "shipment is handover to the destination target")

            actionRepository.saveAll(mutableListOf(action1, action2, action3, action4))

            val defaultPlanTemplate = PlanTemplate(id = 999, name = "General Shipment Template", actions = mutableListOf(action1, action2, action3, action4), temperatureRange = temperatureRange)

            templateRepository.save(defaultPlanTemplate)
            templateRepository.findAll().forEach(::print)
        }
    }

    @Bean
    fun fanoutBindings(): Declarables? {
        val fanoutQueue1 = Queue("fanout.queue1", false)
        val fanoutExchange = FanoutExchange("amq.fanout")
        return Declarables(
                fanoutQueue1,
                fanoutExchange,
                BindingBuilder.bind(fanoutQueue1).to(fanoutExchange))
    }


    @Bean
    fun corsConfigurer(): WebMvcConfigurer? {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200")
            }
        }
    }

    @Bean
    fun modelMapper(): ModelMapper? {
        val modelMapper = ModelMapper()
        modelMapper.configuration.setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper
    }


}