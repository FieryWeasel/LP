//
//  Person.h
//  DroitDeVote
//
//  Created by iem on 04/12/2014.
//  Copyright (c) 2014 iem. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Person : NSObject

@property (strong, nonatomic) NSString *name;
@property (nonatomic) UInt8 age;
@property (strong) Person *spouse;

- (id) initWithAge:(UInt8)newAge;
+(Person *)createPersonWithAge:(UInt8)newAge;
- (BOOL)canLegallyVote;

@end
