package org.estatio.dom.viewmodels;

import java.util.List;

import javax.inject.Inject;

import com.google.common.collect.Lists;

import org.joda.time.LocalDate;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Nature;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.fixturescripts.FixtureScript;

import org.isisaddons.module.excel.dom.ExcelFixture;
import org.isisaddons.module.excel.dom.ExcelFixtureRowHandler;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancy;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancyRepository;

import org.incode.module.country.dom.impl.Country;
import org.incode.module.country.dom.impl.CountryRepository;

import org.estatio.dom.Importable;
import org.estatio.dom.asset.FixedAssetRoleType;
import org.estatio.dom.asset.Property;
import org.estatio.dom.asset.PropertyRepository;
import org.estatio.dom.asset.PropertyType;
import org.estatio.dom.party.Party;
import org.estatio.dom.party.PartyRepository;

import lombok.Getter;
import lombok.Setter;

@DomainObject(
        nature = Nature.VIEW_MODEL,
        objectType = "org.estatio.dom.viewmodels.PropertyImport"
)
public class PropertyImport implements ExcelFixtureRowHandler, Importable {

    @Getter @Setter
    private String atPath;

    @Getter @Setter
    private String reference;

    @Getter @Setter
    private String externalReference;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String fullName;

    @Getter @Setter
    private String city;

    @Getter @Setter
    private String countryCode;

    @Getter @Setter
    private String ownerReference;

    @Getter @Setter
    private String type;

    @Getter @Setter
    private LocalDate openingDate;

    @Getter @Setter
    private LocalDate acquireDate;

    @Getter @Setter
    private LocalDate disposalDate;


//    @Override
//    public List<Class> importAfter() {
//        return Lists.newArrayList(CountryImport.class, OrganisationImport.class, PersonImport.class);
//    }

    @Override
    public List<Object> handleRow(final FixtureScript.ExecutionContext executionContext, final ExcelFixture excelFixture, final Object previousRow) {
        return importData(previousRow);
    }

    // REVIEW: other import view models have @Action annotation here...  but in any case, is this view model actually ever surfaced in the UI?
    public List<Object> importData() {
        return importData(null);
    }

    @Programmatic
    @Override
    public List<Object> importData(final Object previousRow) {

        final Party owner = partyRepository.findPartyByReference(ownerReference);
        final Country country = countryRepository.findCountry(countryCode);
        final ApplicationTenancy appTenancy = applicationTenancyRepository.findByPath(atPath);
        Property property = propertyRepository.findPropertyByReference(reference);
        if (property == null) {
            property = propertyRepository.newProperty(reference, name, PropertyType.valueOf(type), city, country, acquireDate);
        }
        property.setName(name);
        property.setFullName(fullName);
        property.setCountry(country);
        property.setCity(city);
        property.setType(PropertyType.valueOf(type));
        property.setAcquireDate(acquireDate);
        property.setDisposalDate(disposalDate);
        property.setOpeningDate(openingDate);
        property.setExternalReference(externalReference);
        property.addRoleIfDoesNotExist(owner, FixedAssetRoleType.PROPERTY_OWNER, null, null);

        return Lists.newArrayList(property);
    }

    //region > injected services
    @Inject
    private PartyRepository partyRepository;

    @Inject
    private CountryRepository countryRepository;

    @Inject
    private PropertyRepository propertyRepository;

    @Inject
    private ApplicationTenancyRepository applicationTenancyRepository;

    //endregion

}
