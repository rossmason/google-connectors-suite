/*
Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com

The software in this package is published under the terms of the CPAL v1.0
license, a copy of which has been included with this distribution in the
LICENSE.txt file.
 */
package org.mule.google.mirror;

import org.mule.api.MuleEvent;
import org.mule.construct.Flow;
import org.mule.tck.junit4.FunctionalTestCase;

import junit.framework.Assert;
import org.junit.Test;

/**
 * TODO
 */
public class GoogleMirrorConnectorTest extends FunctionalTestCase
{
    @Override
    protected String getConfigResources()
    {
        return "mule-config.xml";
    }


    @Test
    public void testFlow() throws Exception
    {
//        runFlowWithPayloadAndExpect("authorize", "Hello Moto!", "Hello Moto!");
//
//        runFlowWithPayloadAndExpect("insert", new TimelineItem().setText("Hello Moto!"), "Hello Moto!");
    }

    /**
     * Run the flow specified by name and assert equality on the expected output
     *
     * @param flowName The name of the flow to run
     * @param expect The expected output
     */
    protected <T> void runFlowAndExpect(String flowName, T expect) throws Exception
    {
        Flow flow = lookupFlowConstruct(flowName);
        MuleEvent event = getTestEvent(null);
        MuleEvent responseEvent = flow.process(event);

        Assert.assertEquals(expect, responseEvent.getMessage().getPayload());
    }

    /**
     * Run the flow specified by name using the specified payload and assert
     * equality on the expected output
     *
     * @param flowName The name of the flow to run
     * @param expect The expected output
     * @param payload The payload of the input event
     */
    protected <T, U> void runFlowWithPayloadAndExpect(String flowName, T expect, U payload) throws Exception
    {
        Flow flow = lookupFlowConstruct(flowName);
        MuleEvent event = getTestEvent(payload);
        MuleEvent responseEvent = flow.process(event);

        Assert.assertEquals(expect, responseEvent.getMessage().getPayload());
    }

    /**
     * Retrieve a flow by name from the registry
     *
     * @param name Name of the flow to retrieve
     */
    protected Flow lookupFlowConstruct(String name)
    {
        return (Flow) muleContext.getRegistry().lookupFlowConstruct(name);
    }
}