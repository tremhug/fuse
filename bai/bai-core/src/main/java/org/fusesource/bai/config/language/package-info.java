/*
 * Copyright (C) FuseSource, Inc.
 * http://fusesource.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@XmlSchema(namespace = "http://fuse.fusesource.org/schema/bai",
        xmlns = {
                @XmlNs(namespaceURI = Namespaces.DEFAULT_NAMESPACE, prefix = "c"),
                @XmlNs(namespaceURI = AuditConstants.AUDIT_NAMESPACE, prefix = AuditConstants.EXPRESSION_NAMESPACE_PREFIX)
        },
        elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED) package org.fusesource.bai.config.language;

import org.apache.camel.builder.xml.Namespaces;
import org.fusesource.bai.AuditConstants;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlSchema;