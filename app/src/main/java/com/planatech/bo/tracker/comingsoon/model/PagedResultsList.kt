package com.planatech.bo.tracker.comingsoon.model

data class PagedResultsList (
	val dates : Dates,
	val page : Int,
	val results : List<Movie>,
	val total_pages : Int,
	val total_results : Int
)