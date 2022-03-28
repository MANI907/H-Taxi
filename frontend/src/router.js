
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import GrabManager from "./components/GrabManager"

import PaymentManager from "./components/PaymentManager"

import AllocationManagementManager from "./components/AllocationManagementManager"

import RequestAllocationStatus from "./components/RequestAllocationStatus"
export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/grabs',
                name: 'GrabManager',
                component: GrabManager
            },

            {
                path: '/payments',
                name: 'PaymentManager',
                component: PaymentManager
            },

            {
                path: '/allocationManagements',
                name: 'AllocationManagementManager',
                component: AllocationManagementManager
            },

            {
                path: '/requestAllocationStatuses',
                name: 'RequestAllocationStatus',
                component: RequestAllocationStatus
            },


    ]
})
