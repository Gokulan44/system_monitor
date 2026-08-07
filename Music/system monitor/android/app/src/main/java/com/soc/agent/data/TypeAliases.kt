package com.soc.agent.data

import com.soc.agent.api.dto.ScanItemDto
import com.soc.agent.api.dto.ScanRequest
import com.soc.agent.api.dto.ScanResponse as ApiScanResponse

/**
 * Public aliases exposed from the `data` package. The UI layer imports scan
 * DTOs from `com.soc.agent.data` (e.g. `ApkScannerFragment`), while the wire
 * models live in `com.soc.agent.api.dto`. These aliases give the UI clean,
 * stable names without duplicating the DTO definitions.
 */
typealias ScanItemDto = com.soc.agent.api.dto.ScanItemDto
typealias ScanRequest = com.soc.agent.api.dto.ScanRequest
typealias ScanResponse = ApiScanResponse