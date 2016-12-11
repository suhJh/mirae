package com.mirae.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "mirae", ignoreUnknownFields = true)
class MiraeProperties {

	String mode

}
