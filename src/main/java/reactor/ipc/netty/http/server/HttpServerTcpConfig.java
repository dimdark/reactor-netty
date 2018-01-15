/*
 * Copyright (c) 2011-2017 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package reactor.ipc.netty.http.server;

import reactor.ipc.netty.tcp.TcpServer;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Stephane Maldini
 */
final class HttpServerTcpConfig extends HttpServerOperator {

	final Function<? super TcpServer, ? extends TcpServer> tcpServerMapper;

	HttpServerTcpConfig(HttpServer server,
			Function<? super TcpServer, ? extends TcpServer> tcpServerMapper) {
		super(server);
		this.tcpServerMapper = Objects.requireNonNull(tcpServerMapper, "tcpServerMapper");
	}

	@Override
	protected TcpServer tcpConfiguration() {
		return Objects.requireNonNull(tcpServerMapper.apply(source.tcpConfiguration()),
				"tcpServerMapper");
	}
}