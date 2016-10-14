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
package org.estatio.dom.lease.breaks;

import org.estatio.dom.Titled;
import org.incode.module.base.dom.utils.StringUtils;

public enum BreakType implements Titled {

    FIXED(FixedBreakOption.class, BreakOptionEventType.BREAK_DATE),
    ROLLING(RollingBreakOption.class, BreakOptionEventType.BREAK_DATE);

    private Class<? extends BreakOption> cls;

    private BreakOptionEventType breakOptionEventType;

    private BreakType(final Class<? extends BreakOption> cls, BreakOptionEventType type) {
        this.cls = cls;
    }
    
    public Class<? extends BreakOption> getFactoryClass() {
        return cls;
    }

    public String title() {
        return StringUtils.enumTitle(this.toString());
    }


    // //////////////////////////////////////

    public static class Meta {
        private Meta(){}

        public final static int MAX_LEN = 30;
    }

}
