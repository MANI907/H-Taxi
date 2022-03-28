<template>

<v-data-table
    :headers="headers"
    :items="requestAllocationStatus"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'RequestAllocationStatus',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        headers: [
            { text: "id", value: "id" },
        ],
        requestAllocationStatus : [],
    }),
    async created() {
      var temp = await axios.get(axios.backend + '/requestallocationstatuses')

      this.requestAllocationStatus = temp.data._embedded.requestallocationstatuses;

    },
    methods: {
    }
  }
</script>

