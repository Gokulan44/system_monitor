package com.soc.agent.security

/**
 * A single policy violation surfaced to the UI.
 *
 * @param name   short violation label (policy name/type), e.g. "usb_debugging".
 * @param detail human-readable explanation of what the device must do to comply.
 */
data class PolicyViolation(
    val name: String,
    val detail: String
)
