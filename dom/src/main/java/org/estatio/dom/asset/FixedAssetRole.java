package org.estatio.dom.asset;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.VersionStrategy;

import org.joda.time.LocalDate;

import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Where;

import org.estatio.dom.EstatioTransactionalObject;
import org.estatio.dom.party.Party;

@javax.jdo.annotations.PersistenceCapable/*(extensions={
        @Extension(vendorName="datanucleus", key="multitenancy-column-name", value="iid"),
        @Extension(vendorName="datanucleus", key="multitenancy-column-length", value="4"),
    })*/
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "VERSION")
@Bookmarkable(BookmarkPolicy.AS_CHILD)
public class FixedAssetRole extends EstatioTransactionalObject implements Comparable<FixedAssetRole> {

    // {{ Property (asset)
    private FixedAsset asset;

    @Title(sequence = "3", prepend = ":")
    @MemberOrder(sequence = "1")
    @Hidden(where = Where.REFERENCES_PARENT)
    @Disabled
    public FixedAsset getAsset() {
        return asset;
    }

    public void setAsset(final FixedAsset asset) {
        this.asset = asset;
    }

    // }}

    // {{ Party (asset)
    private Party party;

    @Title(sequence = "2", prepend = ":")
    @MemberOrder(sequence = "2")
    @Hidden(where = Where.REFERENCES_PARENT)
    @Disabled
    public Party getParty() {
        return party;
    }

    public void setParty(final Party party) {
        this.party = party;
    }

    // }}

    // {{ Type (asset)
    private FixedAssetRoleType type;

    @Disabled
    @MemberOrder(sequence = "3")
    @Title(sequence = "1")
    public FixedAssetRoleType getType() {
        return type;
    }

    public void setType(final FixedAssetRoleType type) {
        this.type = type;
    }

    // }}

    // {{ StartDate (asset)
    @javax.jdo.annotations.Persistent
    private LocalDate startDate;

    @MemberOrder(sequence = "4")
    @Optional
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    // }}

    // {{ EndDate (asset)
    @javax.jdo.annotations.Persistent
    private LocalDate endDate;

    @MemberOrder(sequence = "5")
    @Optional
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    // }}

    // {{ Comparable impl
    @Hidden
    @Override
    public int compareTo(FixedAssetRole o) {
        return this.getType().compareTo(o.getType());
    }
    // }}

}