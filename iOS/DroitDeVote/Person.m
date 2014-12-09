//
//  Person.m
//  DroitDeVote
//
//  Created by iem on 04/12/2014.
//  Copyright (c) 2014 iem. All rights reserved.
//

#import "Person.h"

@implementation Person

- (id) initWithAge:(UInt8)newAge{
    self = [super init];
    if(self){
        _age = newAge;
    }
    return self;
}

+(Person *)createPersonWithAge:(UInt8)newAge{
    return [[Person alloc] initWithAge:newAge];
}

- (BOOL)canLegallyVote{
    return (_age>17);
}

@end
