/**
 * Mule Google Api Commons
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */


package com.google.gdata.data.appsforyourdomain;

import com.google.gdata.util.common.xml.XmlWriter;
import com.google.gdata.data.Extension;
import com.google.gdata.data.ExtensionDescription;
import com.google.gdata.data.ExtensionPoint;
import com.google.gdata.data.ExtensionProfile;
import com.google.gdata.util.XmlParser.ElementHandler;

import org.xml.sax.Attributes;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A name space element: "apps:name".  Used to help model a user account in
 * Google Apps for Your Domain along with the Login extension.  Has attributes
 * familyName, and givenName.
 *
 * 
 * 
 *
 */
public class Name extends ExtensionPoint implements Extension {
  public static final String EXTENSION_LOCAL_NAME = "name";
  public static final String ATTRIBUTE_FAMILY_NAME = "familyName";
  public static final String ATTRIBUTE_GIVEN_NAME = "givenName";
  private static ExtensionDescription EXTENSION_DESC
     = new ExtensionDescription();

  static {
    EXTENSION_DESC.setExtensionClass(Name.class);
    EXTENSION_DESC.setNamespace(Namespaces.APPS_NAMESPACE);
    EXTENSION_DESC.setLocalName(EXTENSION_LOCAL_NAME);
    EXTENSION_DESC.setRepeatable(false);
  }

  // property "familyName"
  protected String familyName;
  public String getFamilyName() {
    return familyName;
  }
  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  // property "givenName"
  protected String givenName;
  public String getGivenName() {
    return givenName;
  }
  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  /**
   * @return Description of this extension
   */
  public static ExtensionDescription getDefaultDescription() {
    return EXTENSION_DESC;
  }

  @Override
  public void generate(XmlWriter w, ExtensionProfile extensionProfile)
      throws IOException {
    ArrayList<XmlWriter.Attribute> attributes =
      new ArrayList<XmlWriter.Attribute>();

    if (familyName != null) {
      attributes.add(
        new XmlWriter.Attribute(ATTRIBUTE_FAMILY_NAME, familyName)
      );
    }

    if (givenName != null) {
      attributes.add(
        new XmlWriter.Attribute(ATTRIBUTE_GIVEN_NAME, givenName)
      );
    }

    generateStartElement(
      w, Namespaces.APPS_NAMESPACE, EXTENSION_LOCAL_NAME, attributes, null
    );

    // Invoke ExtensionPoint.
    generateExtensions(w, extensionProfile);

    w.endElement(Namespaces.APPS_NAMESPACE, EXTENSION_LOCAL_NAME);
  }

  @Override
  public ElementHandler getHandler(
      ExtensionProfile extProfile, String namespace,
      String localName, Attributes attrs) {
    return new Handler(extProfile);
  }

  /** <apps:name> parser. */
  private class Handler extends ExtensionPoint.ExtensionHandler {
    public Handler(ExtensionProfile extProfile) {
      super(extProfile, Name.class);
    }

    @Override
    public void processAttribute(
        String namespace, String localName, String value) {
      if ("".equals(namespace)) {
        if (ATTRIBUTE_FAMILY_NAME.equals(localName)) {
          familyName = value;
        } else if (ATTRIBUTE_GIVEN_NAME.equals(localName)) {
          givenName = value;
        }
      }
    }
  }
}
