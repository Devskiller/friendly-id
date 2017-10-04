package com.devskiller.friendly_id.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

import com.devskiller.friendly_id.spring.EnableFriendlyId;

@Configuration
@ConditionalOnExpression("${com.devskiller.friendly_id.auto:true}")
@EnableFriendlyId
public class FriendlyIdAutoConfiguration {

}
