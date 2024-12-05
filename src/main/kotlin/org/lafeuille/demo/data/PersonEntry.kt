package org.lafeuille.demo.data

import org.springframework.ldap.odm.annotations.Attribute
import org.springframework.ldap.odm.annotations.DnAttribute
import org.springframework.ldap.odm.annotations.Entry
import org.springframework.ldap.odm.annotations.Id
import javax.naming.Name
import javax.naming.ldap.LdapName

@Entry(
    objectClasses = ["inetOrgPerson", "naturalPerson", "organizationalPerson", "person", "top"],
    base = PersonEntry.DN_BASE,
)
class PersonEntry(
    @Id
    var dn: Name?,
    @Attribute(name = "uid")
    @DnAttribute("uid", index = 3)
    var uid: String?,
    @Attribute(name = "cn")
    var commonName: String?,
    var dateOfBirth: String?,
    @Attribute(name = "sn")
    var surname: String?,
    var givenName: String?,
) {
    constructor() : this(null, null, null, null, null, null)

    companion object {
        const val DN_BASE = "ou=people,dc=lafeuille,dc=org"

        fun uidToName(uid: String): Name = LdapName("uid=$uid,$DN_BASE")
    }
}
