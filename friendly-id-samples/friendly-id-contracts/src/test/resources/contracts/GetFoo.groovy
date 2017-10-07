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
				// FIXME: should be _links
//				links: [[
//						        rel : 'self',
//						        href: 'http://localhost/foos/caffe'
//				        ]]
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