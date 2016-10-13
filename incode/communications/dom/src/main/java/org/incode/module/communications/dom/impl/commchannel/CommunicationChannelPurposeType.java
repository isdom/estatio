/*
 *
 *  Copyright 2012-2014 Eurocommercial Properties NV
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.incode.module.communications.dom.impl.commchannel;

import org.estatio.dom.TitledEnum;

public enum CommunicationChannelPurposeType implements TitledEnum {

    ACCOUNTING("Accounting"),
    INVOICING("Invoicing");

    private String title;

    private CommunicationChannelPurposeType(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }


    // //////////////////////////////////////

    public static class Meta {
        private Meta(){}

        public final static int MAX_LEN = 30;
    }

}
