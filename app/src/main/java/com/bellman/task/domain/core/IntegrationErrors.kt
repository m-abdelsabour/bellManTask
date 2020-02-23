package com.bellman.task.domain.core

class ServerErrorException : Exception()
class UnAuthorizedException : Exception()
class ServerUnreachableException : Exception()
class BadRequest : Exception()
class NotFound : Exception()
class ManyRequest : Exception()