package com.devskiller.friendly_id.jackson;

import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;

public class FriendlyIdAnnotationIntrospector extends NopAnnotationIntrospector {

	private static final long serialVersionUID = 1L;

	@Override
	public Object findSerializer(Annotated am) {
		IdFormat annotation = am.getAnnotation(IdFormat.class);
		if (annotation != null) {
			switch (annotation.value()) {
				case RAW:
					return UUIDSerializer.class;
				case URL62:
					return FriendlyIdSerializer.class;
			}
		}
		return null;
	}

	@Override
	public Object findDeserializer(Annotated annotatedMethod) {
		IdFormat annotation = annotatedMethod.getAnnotation(IdFormat.class);
		if (annotation != null) {
			switch (annotation.value()) {
				case RAW:
					return UUIDDeserializer.class;
				case URL62:
					return FriendlyIdDeserializer.class;
			}
		}
		return null;
	}
}
