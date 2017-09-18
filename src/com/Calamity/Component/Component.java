package com.Calamity.Component;

public interface Component {

    /**
     * Runs on all components before any Render calls.
     * @param millis    Milliseconds since last Update call.
     */
    void Update(int millis);

    /**
     * Runs on all components after all Update calls.
     * @param millis    Milliseconds since last Render call.
     */
    void Render(int millis);

}