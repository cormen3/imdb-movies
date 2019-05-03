package hossein.gheisary.data.remote.model

import hossein.gheisary.data.remote.model.SearchEntity


class SearchResult constructor(
    var Response:Boolean=true,
    var totalResults:Int=0,
    var Search: ArrayList<SearchEntity>
     )