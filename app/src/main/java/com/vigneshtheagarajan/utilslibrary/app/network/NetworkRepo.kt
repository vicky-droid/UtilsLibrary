package com.vigneshtheagarajan.utilslibrary.app.network

import com.vigneshtheagarajan.utils.one.network.baseRepository.BaseRepositorySuspend

class NetworkRepo (service: ApiCommonService) : BaseRepositorySuspend<ApiCommonService>(service) {
    fun getGithubRepos() = run {
        NetRequest.commonService.getGithubRepos()
    }
}
