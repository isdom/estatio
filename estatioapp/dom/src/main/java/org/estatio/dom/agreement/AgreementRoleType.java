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
package org.estatio.dom.agreement;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Unique;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Title;

import org.isisaddons.module.security.dom.tenancy.ApplicationTenancy;

import org.estatio.dom.EstatioDomainObject;
import org.estatio.dom.JdoColumnLength;
import org.estatio.dom.WithTitleComparable;
import org.estatio.dom.apptenancy.ApplicationTenancyInvariantsService;
import org.estatio.dom.apptenancy.WithApplicationTenancyGlobal;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy = IdGeneratorStrategy.NATIVE,
        column = "id")
@Unique(name = "AgreemenRoleType_appliesTo_title_UNQ", members = { "appliesTo", "title" })
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "findByTitle", language = "JDOQL",
                value = "SELECT "
                        + "FROM org.estatio.dom.agreement.AgreementRoleType "
                        + "WHERE title == :title "),
        @javax.jdo.annotations.Query(
                name = "findByAgreementType", language = "JDOQL",
                value = "SELECT "
                        + "FROM org.estatio.dom.agreement.AgreementRoleType "
                        + "WHERE appliesTo == :agreementType "),
        @javax.jdo.annotations.Query(
                name = "findByAgreementTypeAndTitle", language = "JDOQL",
                value = "SELECT "
                        + "FROM org.estatio.dom.agreement.AgreementRoleType "
                        + "WHERE appliesTo == :agreementType && title == :title")
})
@DomainObject(editing = Editing.DISABLED, bounded = true)
public class AgreementRoleType
        extends EstatioDomainObject<AgreementRoleType>
        implements WithTitleComparable<AgreementRoleType>, WithApplicationTenancyGlobal {

    public AgreementRoleType() {
        super("title");
    }

    // //////////////////////////////////////

    @Hidden
    public ApplicationTenancy getApplicationTenancy() {
        return applicationTenancies.findTenancyByPath(ApplicationTenancyInvariantsService.GLOBAL_APPLICATION_TENANCY_PATH);
    }

    // //////////////////////////////////////

    private String title;

    @javax.jdo.annotations.Column(allowsNull = "false", length = JdoColumnLength.TITLE)
    @Title
    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    // //////////////////////////////////////

    private AgreementType appliesTo;

    @javax.jdo.annotations.Column(name = "appliesToAgreementTypeId", allowsNull = "false")
    public AgreementType getAppliesTo() {
        return appliesTo;
    }

    public void setAppliesTo(final AgreementType agreementType) {
        this.appliesTo = agreementType;
    }

    // //////////////////////////////////////

    // //////////////////////////////////////

    public static List<AgreementRoleType> applicableTo(final AgreementType at) {
        return at.getRoleTypesApplicableTo();
    }

}
