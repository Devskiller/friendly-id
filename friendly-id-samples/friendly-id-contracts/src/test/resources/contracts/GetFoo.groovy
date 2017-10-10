org.springframework.cloud.contract.spec.Contract.make {
	request {
		method 'GET'
		url '/foos/caffe'
		headers {
			applicationJsonUtf8()
		}
	}
	response {
		status 200
		body(
				uuid: 'caffe',
				name: 'Foo',
				_links: [
						self: [
								href: 'http://localhost/foos/caffe'
						]

				]
		)
		headers {
			applicationJsonUtf8()
		}
	}
}